(defproject gipf-online "0.0.1"
            :description "A web app that allows users to play the games in Project Gipf"
            :dependencies [[org.clojure/clojure "1.3.0"]
                           [org.clojure/algo.generic "0.1.0"]
                           [noir "1.2.1"]]
            :dev-dependencies [[midje "1.4.0"]
                               [com.stuartsierra/lazytest "1.2.3"]]
            :repositories {"stuart" "http://stuartsierra.com/maven2"}
            :main gipf-online.web.server)
