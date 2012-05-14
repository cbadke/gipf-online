(ns gipf.web
  (:use [ring.adapter.jetty :only [run-jetty]])
  (:use [ring.middleware.params :only [wrap-params]])
  (:use ring.middleware.reload)
  (:use ring.middleware.stacktrace)
  (:use ring.util.response)
  (:use compojure.core)
  (:use hiccup.core)
  (:use hiccup.page))

(defn view-layout [& content]
  (xhtml
    (xhtml-tag "en"
               [:head
                [:meta {:http-equiv "Content-type"
                        :content "text/html; charset=utf-8"}]
                [:title "adder"]]
               [:body 
                [:div.nav 
                           [:a.action {:href "/"} "Home"]
                           [:a.action {:href "/gipf"} "Gipf"]
                           [:a.action {:href "/tzaar"} "Tzaar"]
                           [:a.action {:href "/zertz"} "Zertz"]
                           [:a.action {:href "/dvonn"} "Dvonn"]
                           [:a.action {:href "/yinsh"} "Yinsh"]
                           [:a.action {:href "/punct"} "Punct"]
                           [:a.action {:href "/tamsk"} "Tamsk"]]
                 content])))

(defn view-home []
  (view-layout
    [:h1 "Welcome to Gipf-Online"]))

(defn view-gipf []
  (view-layout
    [:h1 "Play Gipf"]))

(defn view-tzaar []
  (view-layout
    [:h1 "Play Tzaar"]))

(defn view-zertz []
  (view-layout
    [:h1 "Play Zertz"]))

(defn view-dvonn []
  (view-layout
    [:h1 "Play Dvonn"]))

(defn view-yinsh []
  (view-layout
    [:h1 "Play Yinsh"]))

(defn view-punct []
  (view-layout
    [:h1 "Play Punct"]))

(defn view-tamsk []
  (view-layout
    [:h1 "Play Tamsk"]))

(defroutes main-routes
           (GET "/" []
                (view-home))

           (GET "/gipf" []
                (view-gipf))

           (GET "/tzaar" []
                (view-tzaar))

           (GET "/zertz" []
                (view-zertz))

           (GET "/dvonn" []
                (view-dvonn))

           (GET "/yinsh" []
                (view-yinsh))

           (GET "/punct" []
                (view-punct))

           (GET "/tamsk" []
                (view-tamsk))

           (ANY "/*" [path]
                (redirect "/")))

(def handler (wrap-params main-routes))

(def app
  (-> #'handler
    (wrap-reload '[gipf.web])
    (wrap-stacktrace)))

(defn -main [port]
  (run-jetty app {:port (Integer. port)}))
