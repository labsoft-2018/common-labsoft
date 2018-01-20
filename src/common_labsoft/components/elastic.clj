(ns common-labsoft.components.elastic
  (:require [com.stuartsierra.component :as component]
            [clojurewerkz.elastisch.rest :as esr]
            [common-labsoft.protocols.elastic :as protocols.elastic]
            [common-labsoft.protocols.config :as protocols.config]))

(defrecord Elastic [config]
  component/Lifecycle
  (start [this]
    (let [host (protocols.config/get! config :elastic-endpoint)]
      (assoc this :conn (esr/connect host))))
  (stop [this]
    (dissoc this :conn))
  protocols.elastic/Elastic
  (connection [this]
    (:conn this)))

(defn new-elastic []
  (map->Elastic {}))