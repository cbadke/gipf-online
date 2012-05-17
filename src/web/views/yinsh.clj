(ns web.views.yinsh
  (:use [noir.core :only [defpartial defpage]]
        [web.views.common]
        [hiccup.core]
        [hiccup.page-helpers :only [include-css include-js html5 link-to]]))

(defpage "/yinsh" []
  (page-wrapper
    [:h1 "Play Yinsh"]))

