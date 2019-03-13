(ns ejercicio.views
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [goog.dom :as gdom]
            [goog.json :as gjson]
            [ejercicio.utils :as u]))

(defn- submit-fn [event]
  (.preventDefault event)
  (let [this-form (u/oget  event 'target)]
    #_(rf/dispatch [:add-entry (form-values->clj this-form)])))

(defn tree-viewer []
  [:section.section
   [:div.container;.columns.is-centered
    [:h1.title "How to use it"]
    [:h2.subtitle
     "This is a simple  calculator for your mortgage expenses "
     [:div.columns
      [:div.column
           [:ul.treeViewer
            [:li
             [:span.caret "Beverages"]
             [:ul.nested
              [:li "Water"]
              [:li "Coffee"]
              [:li
               [:span.caret "Tea"]
               [:ul.nested
                [:li "Black Tea"]
                [:li "White Tea"]
                [:li
                 [:span.caret "Green Tea"]
                 [:ul.nested
                  [:li "Sencha"]
                  [:li "Gyokuro"]
                  [:li "Matcha"]
                  [:li "Pi Lo Chun"]]]]]]]]]
      [:div.column
       [:form.field.is-grouped    {:on-submit submit-fn}
        [:div.control [:input.input.button.is-link  {:type "submit" :value "Load tree"}]]
        [:div.control [:input.input.is-info {:type "text" :value "Enter path"}]]]]]]]])

