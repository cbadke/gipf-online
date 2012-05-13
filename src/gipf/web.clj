(ns gipf.web
  (:use [ring.adapter.jetty :only [run-jetty]])
  (:use [ring.middleware.params :only [wrap-params]])
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
               [:body content])))

(defn view-input []
  (view-layout
    [:h2 "add two numbers"]
    [:form {:method "post" :action "/"}
     [:input.math {:type "text" :name "a"}] [:span.math " + "]
     [:input.math {:type "text" :name "b"}] [:br]
     [:input.action {:type "submit" :value "add"}]]))

(defn view-output [a b sum]
  (view-layout
    [:h2 "two numbers added"]
    [:p.math a " + " b " = " sum]
    [:a.action {:href "/"} "add more numbers"]))

(defn parse-input [a b]
  [(read-string a) (read-string b)])

(defroutes main-routes
           (GET "/" []
                (view-input))

           (POST "/" [a b]
                 (let [[a b] (parse-input a b)
                       sum (+ a b)]
                   (view-output a b sum))))

(def app (wrap-params main-routes))

(defn -main [port]
  (run-jetty app {:port (Integer. port)}))
