(ns timbre-util.log
  "Convenience functions on top of a timbre configuration which are useful for the SkyBet Data team"
  (:require [taoensso.timbre :as timbre :refer [default-output-fn color-str]]))

(defonce ^{:private true} configured? (atom false))

(defonce ^{:private true} log? (atom false))

(def bla (atom nil))

(def ^{:private true} timbre-config
  (let [colors {:info :green, :warn :yellow, :error :red, :fatal :purple, :report :blue}]
    {:level :debug
     :output-fn default-output-fn
     :appenders
       {:color-appender
         {:enabled?   false
          :async?     false
          :min-level  nil
          :rate-limit nil
          :output-fn  :inherit
          :fn (fn [{:keys [error? level output-fn vargs_ msg-fn] :as data}]
                (binding [*out* (if error? *err* *out*)]
                 (let [data (if (= (first @vargs_) "\b")
                              (assoc data :?err_ (second @vargs_))
                              data)]
                   (if-let [color (colors level)]
                     (println (color-str color (output-fn data)))
                     (println (output-fn data))))))}}}))

(defn set-config!
  "Configure timbre to use different colors depending on logging level."
  [] (timbre/set-config! timbre-config))

(defmacro with-config
  "Execute timbre fns in the context of this library config."
  [& body]
  `(do (when-not @configured?
         (set-config!)
         (reset! configured? true))
       (do ~@body)))

(defn toggle!
  "Toggles logging on/off"
  [& [on?]]
  (with-config
    (let [on? (if (nil? on?) (swap! log? not) on?)]
      (timbre/swap-config! assoc-in [:appenders :color-appender :enabled?] on?)
      on?)))
