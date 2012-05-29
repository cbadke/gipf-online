(ns gipf-online.web.views.dvonn
  (:use [noir.core :only [defpartial defpage]]
        [gipf-online.web.views.common :only [page-wrapper]]
        [hiccup.core]
        [hiccup.page-helpers :only [include-css include-js html5 link-to]]))

(defpage "/dvonn" []
  (page-wrapper
    [:h1 "Play DVONN"]))
