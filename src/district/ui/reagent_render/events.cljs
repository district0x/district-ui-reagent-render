(ns ^:figwheel-always district.ui.reagent-render.events
  (:require [district.ui.reagent-render.spec :as spec]
            [reagent.core :as r]
            [re-frame.core :as re-frame]))

(def interceptors [re-frame/trim-v])

(re-frame/reg-event-fx
 ::start
 [interceptors]
 (fn [_ [opts]]
   {:dispatch [::render opts]}))

(re-frame/reg-event-fx
 ::render
 [interceptors]
 (fn [_ [{:keys [target component-ref] :as opts}]]
   {::render-fx [target component-ref]}))

(re-frame/reg-fx
 ::render-fx
 (fn [[target component-ref]]
   (re-frame/clear-subscription-cache!)
   (r/render [component-ref] target)))
