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
nil

user=> (info "flapjacks are yummy")
; doesn't print anything (using leiningen here)
nil

user=> (log/toggle!) ; toggles logging on
true

user=> (info "brownies ♡")
16-01-25 11:58:07 hungryman-MacBook-Pro.local INFO [skybet.some.ns] - brownies ♡
nil

user=> (log/timbre! false) ; disables logging regardless if it's enabled or not
false
```

### Features
-------
  - Beautiful colors for different levels of logging
  - Logging defaults to disabled when using leiningen and enabled otherwise
  - Toggling logs on or off


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
