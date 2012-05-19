(ns web.views.home
  (:use [noir.core :only [defpartial defpage]]
        [web.views.common :only [page-wrapper]]
        [hiccup.core]
        [hiccup.page-helpers :only [include-css include-js html5 link-to]]))

(defpage "/" []
  (page-wrapper
    [:h1 "Welcome to GIPF Online"]))
