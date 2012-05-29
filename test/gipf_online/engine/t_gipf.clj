(ns gipf-online.engine.t-gipf
  (:use [gipf-online.engine.gipf])
  (:use [midje.sweet]))

(fact "can create board"
  (count ((create-empty-board) :spaces)) => 61)

(fact "board is empty"
  (every? #(= :empty (:colour (val %))) ((create-empty-board) :spaces)) => true)

(fact "white starts"
  (:current-player (create-empty-board)) => :white)

(fact "Moving with start not on edge does nothing"
  (move (create-empty-board) :B3 :B4) => (create-empty-board))

(fact "Moving in from edge places piece"
  (:colour (:B2 (:spaces (move (create-empty-board) :A1 :B2)))) => :white)
