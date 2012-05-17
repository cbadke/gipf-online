(ns web.views.common
  (:use [noir.core :only [defpartial defpage]]
        [hiccup.core]
        [hiccup.page-helpers :only [include-css include-js html5 link-to]]))

(defpartial page-wrapper [& content]
  (let [title "GIPF Online"]
    (html5
      [:head
       [:title title]
       (include-css "/css/common.css")
       (include-css "https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/themes/smoothness/jquery-ui.css")
       (include-js  "https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js")
       (include-js  "https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js")]
      [:body 
       [:div#page-container
        [:div#header
         [:a {:href "/"} "Home"]
         [:a {:href "/gipf"} "GIPF"]
         [:a {:href "/tzaar"} "TZAAR"]
         [:a {:href "/zertz"} "ZÈRTZ"]
         [:a {:href "/dvonn"} "DVONN"]
         [:a {:href "/yinsh"} "YINSH"]
         [:a {:href "/punct"} "PÜNCT"]
         [:a {:href "/tamsk"} "TAMSK"]]
        [:div#body-wrapper content]
        [:div#footer 
         [:div 
          [:a {:href "http://creativecommons.org/licenses/by-nc-sa/3.0/"} 
           [:img {:src "http://i.creativecommons.org/l/by-nc-sa/3.0/88x31.png" :alt "Creative Commons License" :title "Creative Commons License"}]]
          [:a.github {:href "https://github.com/cbadke/gipf-online"}
           [:img {:src "/images/github-icon.jpg" :alt "Fork me on Github" :title "Fork me on Github"}]]]
         [:div 
          [:a {:href "http://www.gipf.com"} "The GIPF Project"]
          [:span " is created by "]
          [:a {:href "http://en.wikipedia.org/wiki/Kris_Burm"} "Kris Burm"]]]]])))

(defpage "/" []
  (page-wrapper
    [:h1 "Welcome to GIPF Online"]))

