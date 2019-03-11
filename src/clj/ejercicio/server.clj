(ns ejercicio.server
  (:require
    [ejercicio.config :refer [config]]
    ;; [clj-http.client :as client]
    [clojure.instant :as instant]
    ;; [jsonista.core :as json]
    [ring.adapter.jetty :as jetty]
    [ring.middleware.params :as params]
    [reitit.ring.middleware.muuntaja :as muuntaja]
    [muuntaja.core :as m]
    [mount.core :refer [defstate]]
    [reitit.ring.coercion :as coercion]
    [reitit.ring :as ring]
    [taoensso.timbre :as timbre]
    [ejercicio.routes :as routes]))


#_(def mapper
    (json/object-mapper
      {:encode-key-fn name
       :decode-key-fn  keyword}))


(def app
  (ring/ring-handler
    (ring/router
      [routes/routes]
      {:data {:muuntaja m/instance
              :middleware [params/wrap-params
                           muuntaja/format-middleware
                           coercion/coerce-exceptions-middleware
                           coercion/coerce-request-middleware
                           coercion/coerce-response-middleware]}})
    (ring/create-default-handler)))


(defn start-server [opts]
  (try
   (timbre/info (str "Starting webserver server"))
   (let [the-server (jetty/run-jetty #'app  opts)]
    (timbre/info (str "listening on port " (:port opts)))
    the-server)
   (catch Exception e (timbre/info "An error occured on  service startup " (.getMessage e)))))


(defn stop-server [server]
  (try
   (timbre/info  "Stopping server")
   (.stop server)
   (catch Exception e (timbre/info "An error occured when stopping the server " (.getMessage e)))))


(defstate server
 :start (-> config :webserver  start-server)
 :stop  (stop-server server))

