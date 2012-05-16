(ns web.views.common
  (:use [noir.core :only [defpartial defpage]]
        [hiccup.core]
        [hiccup.page-helpers :only [include-css include-js html5 link-to]]))

(defpartial page-wrapper [& content]
  (let [title "Gipf Online"]
    (html5
      [:head
       [:title title]
       (include-css "https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/themes/smoothness/jquery-ui.css")
       (include-js  "https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js")
       (include-js  "https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js")]
      [:body 
       [:div#nav 
        [:a.action {:href "/"} "Home"]
        [:a.action {:href "/gipf"} "Gipf"]
        [:a.action {:href "/tzaar"} "Tzaar"]
        [:a.action {:href "/zertz"} "Zertz"]
        [:a.action {:href "/dvonn"} "Dvonn"]
        [:a.action {:href "/yinsh"} "Yinsh"]
        [:a.action {:href "/punct"} "Punct"]
        [:a.action {:href "/tamsk"} "Tamsk"]]
       [:div#wrapper content]
       [:div#footer ]])))

(defpage "/" []
  (page-wrapper
    [:h1 "Welcome to Gipf-Online"]))

