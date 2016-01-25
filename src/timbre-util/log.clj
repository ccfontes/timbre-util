(ns timbre-util.log
  "Convenience functions on top of a timbre configuration which are useful for the SkyBet Data team"
  (:require [taoensso.timbre :as timbre :refer [default-output-fn color-str]]
            [environ.core :refer [env]]
            [taoensso.timbre :as timbre :refer [info]]))

(defn config-timbre! []
  (let [colors {:info :green, :warn :yellow, :error :red, :fatal :purple, :report :blue}]
    (timbre/set-config!
      {:level :debug
       :output-fn default-output-fn
       :appenders
         {:color-appender
           {:enabled?   true
            :async?     false
            :min-level  nil
            :rate-limit nil
            :output-fn  :inherit
            :fn (fn [{:keys [error? level output-fn] :as data}]
                  (binding [*out* (if error? *err* *out*)]
                    (if-let [color (colors level)]
                      (println (color-str color (output-fn data)))
                      (println (output-fn data)))))}}})))

(config-timbre!)

; if production environment, then not using leiningen
(defonce log? (-> :lein-version env not atom))

(defn toggle!
  "Toggles logging on/off"
  [& [on?]]
  (let [on? (if (nil? on?) (not @log?) on?)]
    (timbre/swap-config! assoc-in [:appenders :color-appender :enabled?] on?)
    (reset! log? on?)))

 (log! false)
