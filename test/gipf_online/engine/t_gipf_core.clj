(ns gipf-online.engine.t-gipf-core
  (:use [gipf-online.engine.gipf-core])
  (:use [midje.sweet]))

(fact "can create board columns"
  (sort (keys (create-column 1 4 4))) => '(:A1 :A2 :A3)
  (sort (keys (create-column 2 4 4))) => '(:B1 :B2 :B3 :B4)
  (sort (keys (create-column 3 4 4))) => '(:C1 :C2 :C3 :C4)
  (sort (keys (create-column 4 4 4))) => '(:D1 :D2 :D3))

(fact "spaces take get coordinate"
  (keys (create-space :A1 :empty 9 9)) => '(:A1))

(fact "spaces take colour"
  (((create-space :A1 :black 9 9) :A1) :colour) => :black)
