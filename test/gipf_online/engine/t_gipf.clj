(ns gipf-online.engine.t-gipf
  (:use [gipf-online.engine.gipf])
  (:use [gipf-online.engine.gipf-core])
  (:use [midje.sweet]))

(fact "Moving with start not on edge does nothing"
  (move (create-board) :B3 :B4) => (create-board))

(fact "Moving in from edge places piece"
  (:colour (:B2 (:spaces (move (create-board) :A1 :B2)))) => :white)

;(fact "Current player toggles after successful move"
;  (:current-player (move (create-empty-board) :A1 :B2)) => :black)
