(ns gipf-online.engine.t-gipf-core
  (:use [gipf-online.engine.gipf-core])
  (:use [midje.sweet]))

(fact "can create board columns"
  (sort (keys (create-column 1))) => '(:A1 :A2 :A3 :A4 :A5)
  (sort (keys (create-column 2))) => '(:B1 :B2 :B3 :B4 :B5 :B6)
  (sort (keys (create-column 3))) => '(:C1 :C2 :C3 :C4 :C5 :C6 :C7)
  (sort (keys (create-column 9))) => '(:I1 :I2 :I3 :I4 :I5))

(fact "spaces take get coordinate"
  (keys (create-space :A1 :empty)) => '(:A1))

(fact "spaces take colour"
  (((create-space :A1 :black) :A1) :colour) => :black)

(fact "edge positions can see inner positions"
  (up-right :A1) => :B2)

(fact "inner positions cannot see edge positions"
  (up-right :H2) => nil)

(fact "up-right increases row on left side of board"
  (up-right :B3) => :C4)

(fact "up-right keeps row on right side of board"
  (up-right :E5) => :F5)
