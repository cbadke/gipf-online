(ns gipf-online.engine.t-gipf
  (:use [gipf-online.engine.gipf])
  (:use [midje.sweet]))

(fact "can create board columns"
  (sort (keys (create-column 1 4 4))) => '(:A1 :A2 :A3)
  (sort (keys (create-column 2 4 4))) => '(:B1 :B2 :B3 :B4)
  (sort (keys (create-column 3 4 4))) => '(:C1 :C2 :C3 :C4)
  (sort (keys (create-column 4 4 4))) => '(:D1 :D2 :D3))

(fact "can create board"
  (count (create-board 3 3)) => 7
  (count (create-board 9 9)) => 61)
