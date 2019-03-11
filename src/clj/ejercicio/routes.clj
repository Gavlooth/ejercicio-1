(ns ejercicio.routes
  (:require
    ;; [jsonista.core :as json]
    [taoensso.timbre :as timbre]))


(def routes
 ["/generator"
  ["/file_tree"
    {:get (fn [& _]
           {:status 200
            :body "succesfull"})}]])





