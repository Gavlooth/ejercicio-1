(ns user
 (:require [clojure.tools.namespace.repl :as tn]
           [mount.core :as mount]
           [ejercicio.server :refer [server]]
           [ejercicio.config :refer [config]]))


(defn go []
 (set! *warn-on-reflection* true)
 (mount/start #'config  #'server))


(defn refresh-all []
 (mount/stop)
 (tn/refresh-all))


(defn reset []
 (mount/stop)
 (tn/refresh)
 (go))
