#!/usr/bin/env boot

(set-env! :dependencies '[])

(defn -main [& args]
  (println (apply str args)))
