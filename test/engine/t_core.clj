(ns engine.t-core
  (:use [engine.core])
  (:use [midje.sweet]))

(fact "we can add"
  (+ 1 1) => 2)
