(ns ejercicio.routes
  (:require
    ;; [jsonista.core :as json]
    [taoensso.timbre :as timbre]
    [clojure.java.io :as io]
    [clojure.string :as str]
    [cognitect.transit :as tr])
  (:import (java.io ByteArrayOutputStream ByteArrayInputStream)))


(defn as-tree [data]
 (map (fn [x]
         (let [[k vs] x]
           (cons k (as-tree (keep next vs)))))
      (group-by first data)))


(defn- get-path [file]
      (.getPath ^java.io.File  file))


(defn- is-dir? [file]
      (.isDirectory ^java.io.File file))


(defn seperate-leafs [file]
  (let [splited-path (str/split (get-path file) (re-pattern java.io.File/separator))]
    (if (is-dir? file)
       splited-path
      (conj
        (vec (butlast splited-path))
        {:type  (last (last (re-seq  #"\.(.*)" (get-path file))))
         :path (get-path file)}))))


(defn path->file-tree [path]
  (let [files (map seperate-leafs (file-seq (io/file path)))]
    (as-tree files)))


(def routes
  ["/api"
   ["/get_files"
    {:get  (fn [{{:strs [path]}  :params :as  request}]
             (let [body (ByteArrayOutputStream. 4096)]
               (tr/write  (tr/writer body :json) (path->file-tree path))
               {:status 200 :body (ByteArrayInputStream. (.toByteArray body))}))}]])


