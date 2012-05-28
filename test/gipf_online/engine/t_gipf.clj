(ns gipf-online.engine.t-gipf
  (:use [gipf-online.engine.gipf])
  (:use [midje.sweet]))

(fact "can create board"
  (count ((create-empty-board) :spaces)) => 61)

(fact "board is empty"
  (every? #(= :empty (:colour (val %))) ((create-empty-board) :spaces)) => true)

(fact "white starts"
  (:current-player (create-empty-board)) => :white)
