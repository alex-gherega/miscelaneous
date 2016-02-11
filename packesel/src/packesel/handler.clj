(ns packesel.handler
  (:use [analemma.xml :only [emit add-content add-attrs
			     parse-xml transform-xml filter-xml]]
	analemma.svg
	[clojure.java.io :only [file]])

  (:require [packesel.config :as pakonf]
            [packesel.koch.svg :as pakog]
            [hiccup.core :as hicore]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/alex-gherega/packesel/koch" []
       (pakog/reset-koch)
       (hicore/html [:body
                     (emit (svg
                            {:width 3000 :height 3000 :x 200 :y 100 :viewPort "30 30 200 200"}
                            (-> (path (into [:M @pakog/s-point] (pakog/do-koch-svg 4)))
                                (style :stroke "#660000"  :fill :none  :stroke-dasharray "3000"
                                       :stroke-dashoffset "3000" :animation "dash 5s linear forwards")
                                )))
                     [:style {:media "screen" :type "text/css"} "@keyframes dash {to {stroke-dashoffset: 0;}}"]])))

(def app
  (wrap-defaults app-routes site-defaults))

;; <!DOCTYPE html>
;; <html>

;; <body>
;; The content of the body element is displayed in your browser.
;; <?xml version="1.0"?>
;; <svg width="200" height="200"
;; viewPort="0 0 200 200" version="1.1"
;; xmlns="http://www.w3.org/2000/svg">

;; <path d="M10 10 L 90 10" style=" stroke: #660000;  fill: none; stroke-dasharray: 1000;
;;   stroke-dashoffset: 1000; animation: dash 5s linear forwards;
;; " id="DD">

;; </rect>

;; </svg>
;; <style media="screen" type="text/css">
;; @keyframes dash {
;;                  to {
;;                      stroke-dashoffset: 0;
;;                      }</style>
;;                  </body>

;;                  </html>
