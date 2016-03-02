timbre-util
-------
Convenience functions on top of a timbre configuration which are useful for the SkyBet Data team.

### Install
-------
Add the following entry to the `:dependencies` vector of your `project.clj` file:

[![clojars version](https://clojars.org/ccfontes/timbre-util/latest-version.svg?raw=true)]
(https://clojars.org/ccfontes/timbre-util)

### Usage
-------
Toggling logs on or off:
```clj
user=> (require '[timbre-util.log :as log])
user=> (require '[taoensso.timbre :as timbre])
nil

user=> (log/set-config!)
;{:level :debug, :output-fn #<timbre$default_output_fn taoensso.timbre$default_output_fn@17870ed0>, :appenders {:color-appender {:enabled? false, :async? false, :min-level nil, :rate-limit nil, :output-fn :inherit, :fn #<log$fn__2628$fn__2630 timbre_util.log$fn__2628$fn__2630@58235e48>}}}

user=> (timbre/info "flapjacks are yummy")
; default is to not print anything
nil

user=> (log/toggle!) ; toggles logging on
true

user=> (timbre/info "brownies ♡")
; 16-01-25 11:58:07 hungryman-MacBook-Pro.local INFO [skybet.some.ns] - brownies ♡

; using "\b" there will still enable formated stack traces when using timbre-util,
; but print a one line log otherwise.
user=> (timbre/error "\b" (Exception. "Cookies and cupcakes not found"))

user=> (log/timbre! false) ; disables logging regardless if it's enabled or not
false
```

### Features
-------
Running `(config-timbre!)` will do the following:
  - set up beautiful colors for different levels of logging
  - disable logging until you run `(toggle!)`
  - Toggling logs on or off
  - timbre-util only formated stacktraces for exceptions (useful for development)


### Wishlist
-------
  - `project.clj` config:
    - change colors
    - change enable logging default
    - inject utility fns on clojure.core for convenience

### License
-------
Copyright (C) 2016 Sky Betting and Gaming.

Released under [The MIT License (MIT)](http://opensource.org/licenses/MIT).
