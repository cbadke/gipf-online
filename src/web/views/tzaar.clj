(ns web.views.tzaar
  (:use [noir.core :only [defpartial defpage]]
        [web.views.common :only [page-wrapper]]
        [hiccup.core]
        [hiccup.page-helpers :only [include-css include-js html5 link-to]]))

(defpage "/tzaar" []
  (page-wrapper
    [:h1 "Play Tzaar"]))
