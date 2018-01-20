(ns common-labsoft.elastic.api
  (:require [common-labsoft.protocols.elastic :as protocols.elastic]
            [clojurewerkz.elastisch.rest.index :as esi]
            [clojurewerkz.elastisch.rest.document :as esd]))


(defn conn [elastic] (protocols.elastic/connection elastic))

(defn create-index [elastic index] (esi/create (conn elastic) index))
(defn insert [elastic index type doc] (esd/create (conn elastic) index type doc))
(defn lookup [elastic index type id] (esd/get (conn elastic) index type id))
(defn update [elastic index type id doc] (esd/replace (conn elastic) index type id doc))
(defn delete [elastic index type id] (esd/delete (conn elastic) index type id))