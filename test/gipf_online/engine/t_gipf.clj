(ns gipf-online.engine.t-gipf
  (:use [gipf-online.engine.gipf])
  (:use [midje.sweet]))

(fact "can create board"
  (count (create-board 3 3)) => 7
  (count (create-board 9 9)) => 61)
