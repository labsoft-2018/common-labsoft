(ns common-labsoft.time
  (:require [schema.core :as s]
            [cheshire.custom]
            [cheshire.generate]
            [clj-time.coerce :as time.coerce]
            [clj-time.core :as time]
            [clj-time.local :as time.local]))

(def LocalDateTime org.joda.time.DateTime)
(def LocalDate org.joda.time.LocalDate)

(s/defn date-time->inst :- java.util.Date
  [date-time :- LocalDateTime]
  (time.coerce/to-date date-time))

(s/defn local-date->inst :- java.util.Date
  [date :- LocalDate]
  (time.coerce/to-date date))

(s/defn inst->local-date :- LocalDate
  [date-time :- LocalDateTime]
  (time.coerce/to-local-date date-time))

(s/defn inst->date-time :- LocalDateTime
  [date :- LocalDate]
  (time.coerce/to-local-date-time date))

(s/defn str->local-date-time :- LocalDateTime
  [str :- s/Str]
  (time.coerce/from-string str))

(s/defn str->local-date :- LocalDate
  [str :- s/Str]
  (-> str str->local-date-time time.coerce/to-local-date))

(s/defn now :- LocalDateTime [] (time/now))
(s/defn local-now :- LocalDateTime [] (time.local/local-now))
(s/defn today :- LocalDate [] (time/today))

(s/defn coerce-to-local-date-time :- LocalDateTime
  [v]
  (time.coerce/to-date-time v))

(s/defn coerce-to-local-date :- LocalDate
  [v]
  (time.coerce/to-local-date v))

(cheshire.generate/add-encoder LocalDateTime (fn [val writer]
                                               (cheshire.generate/encode-str (str val) writer)))

(cheshire.generate/add-encoder LocalDate (fn [val writer]
                                           (cheshire.generate/encode-str (str val) writer)))