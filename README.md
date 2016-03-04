timbre-util
-------
Convenience functions on top of a timbre configuration which are useful for the
SkyBet Data team.

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

user=> (log/toggle!)
; toggles logs off
false

user=> (timbre/info "flapjacks are yummy")
; doesn't print anything
nil

user=> (log/toggle!) ; toggles logging on
true

user=> (timbre/info "brownies ♡")
; 16-01-25 11:58:07 hungryman-MacBook-Pro.local INFO [skybet.some.ns] - brownies ♡

; using exception not as first arg will still enable formatted stack traces
; when using timbre-util, but print a one line log otherwise.
user=> (timbre/error "\b" (Exception. "Cookies and cupcakes not found"))

user=> (log/timbre! false) ; disables logging
false
```

### Features
-------
Running `(log/toggle! true)` will give you:
  - beautiful colors for different levels of logging
  - `timbre-util` only formatted stack traces for exceptions (useful for development)

Running `(log/toggle! false)` will disable logging all together.


### Wishlist
-------
  - `project.clj` config:
    - user can change colors
    - user can change enable logging default

### License
-------
Copyright (C) 2016 Sky Betting and Gaming.

Released under [The MIT License (MIT)](http://opensource.org/licenses/MIT).
