(ns gipf-online.web.views.common
  (:use [noir.core :only [defpartial defpage]]
        [hiccup.core]
        [hiccup.page-helpers :only [include-css include-js html5 link-to]]))

(defn script-inclusions []
  (list
    (include-css "/css/common.css")
    (include-css "/libs/jqueryui/1.8.18/themes/smoothness/jquery-ui.css")
    (include-js  "/libs/jquery/1.7.2/jquery.min.js")
    (include-js  "/libs/jqueryui/1.8.18/jquery-ui.min.js")
    (include-js  "/javascript/navigation.js")))

(defn nav-bar []
  (list 
    [:a#home-link {:class "nav-link" :href "/"}]
    [:a#gipf-link {:class "nav-link" :href "/gipf"}]
    [:a#tzaar-link {:class "nav-link" :href "/tzaar"}]
    [:a#zertz-link {:class "nav-link" :href "/zertz"}]
    [:a#dvonn-link {:class "nav-link" :href "/dvonn"}]
    [:a#yinsh-link {:class "nav-link" :href "/yinsh"}]
    [:a#punct-link {:class "nav-link" :href "/punct"}]
    [:a#tamsk-link {:class "nav-link" :href "/tamsk"}]))

(defn footer []
  (list
    [:div 
     [:a#github-link {:href "https://github.com/cbadke/gipf-online"}
      [:img {:src "/images/github-icon.jpg" :alt "Fork me on Github" :title "Fork me on Github"}]]
     [:a#cc-link {:href "http://creativecommons.org/licenses/by-nc-sa/3.0/"} 
      [:img {:src "http://i.creativecommons.org/l/by-nc-sa/3.0/88x31.png" :alt "Creative Commons License" :title "Creative Commons License"}]]]
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
