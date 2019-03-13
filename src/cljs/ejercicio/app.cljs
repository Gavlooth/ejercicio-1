(ns ejercicio.app
  (:require [reagent.core :as r]
            [goog.dom :as gdom]
            [ejercicio.views :as views]))


(defn app []
  [views/tree-viewer])


(defn ^:export main [ & more]
  (r/render
   [app]
   (gdom/getElement "app")))


(main)

