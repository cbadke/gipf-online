(ns web.views.zertz
  (:use [noir.core :only [defpartial defpage]]
        [web.views.common :only [page-wrapper]]
        [hiccup.core]
        [hiccup.page-helpers :only [include-css include-js html5 link-to]]))

(defpage "/zertz" []
  (page-wrapper
    [:h1 "Play ZÈRTZ"]))


