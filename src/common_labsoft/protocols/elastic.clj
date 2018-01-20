(ns common-labsoft.protocols.elastic)

(defprotocol Elastic
  (connection [this])