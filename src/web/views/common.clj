(ns web.views.common
  (:use [noir.core :only [defpartial defpage]]
        [hiccup.core]
        [hiccup.page-helpers :only [include-css include-js html5 link-to]]))

(defn script-inclusions []
  (html5
    (include-css "/css/common.css")
    (include-css "https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/themes/smoothness/jquery-ui.css")
    (include-js  "https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js")
    (include-js  "https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js")))

(defn nav-bar []
  (html5
    [:div [:a {:href "/"} "Home"]]
    [:div [:a {:href "/gipf"} "GIPF"]]
    [:div [:a {:href "/tzaar"} "TZAAR"]]
    [:div [:a {:href "/zertz"} "ZÈRTZ"]]
    [:div [:a {:href "/dvonn"} "DVONN"]]
    [:div [:a {:href "/yinsh"} "YINSH"]]
    [:div [:a {:href "/punct"} "PÜNCT"]]
    [:div [:a {:href "/tamsk"} "TAMSK"]]))

(defn footer []
  (html5
    [:div 
     [:a#cc-link {:href "http://creativecommons.org/licenses/by-nc-sa/3.0/"} 
      [:img {:src "http://i.creativecommons.org/l/by-nc-sa/3.0/88x31.png" :alt "Creative Commons License" :title "Creative Commons License"}]]
     [:a#github-link {:href "https://github.com/cbadke/gipf-online"}
      [:img {:src "/images/github-icon.jpg" :alt "Fork me on Github" :title "Fork me on Github"}]]]
    [:div 
     [:a {:href "http://www.gipf.com"} "The GIPF Project"]
     [:span " is created by "]
     [:a {:href "http://en.wikipedia.org/wiki/Kris_Burm"} "Kris Burm"]]))
  
(defpartial page-wrapper [& content]
  (let [title "GIPF Online"]
    (html5
      [:head
       [:title title]
       (script-inclusions)]
      [:body 
       [:div#page-container
        [:div#header]
        [:div#body-container
         [:div#nav-container (nav-bar)]
         [:div#content-container content]]
        [:div#footer (footer)]]])))
