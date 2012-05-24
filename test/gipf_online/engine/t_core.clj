(ns gipf-online.engine.t-core
  (:use [gipf-online.engine.core])
  (:use [midje.sweet]))

(fact "can create hex board"
  (count (create-hex-board 3 3)) => 7
  (count (create-hex-board 9 9)) => 61)

(fact "can create hex board columns"
  (sort (keys (create-hex-column 1 4 4))) => '(:A1 :A2 :A3)
  (sort (keys (create-hex-column 2 4 4))) => '(:B1 :B2 :B3 :B4)
  (sort (keys (create-hex-column 3 4 4))) => '(:C1 :C2 :C3 :C4)
  (sort (keys (create-hex-column 4 4 4))) => '(:D1 :D2 :D3))
