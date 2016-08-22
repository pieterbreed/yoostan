(ns yoostan.core
  (:require [yoostan-lib.inventory :as inv]
            [clojure.tools.cli :refer [parse-opts]]
            [environ.core :refer [env]]
            [clojure.data.json :as json])
  (:gen-class))

(def cli-options
  [["-u" "--user USERNAME" "The SSH username to connect WITH"
    :default (env :user)]
   ["-h" "--host HOSTNAME" "The SSH hostname to connect TO"]
   ["-p" "--port PORT" "The SSH port number to connect TO"
    :default 22
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]])

(defn single-machine-inventory
  "Creates a repository appropriate for single-server development"
  [un host port]
  (let [h1 [un host port]]
    (-> inv/empty-inventory
        (inv/target-> h1 {"ansible_host" host
                          "ansible_user" un})
        (inv/target->group-> h1 "java-hosts")
        (inv/inv->ansible--list))))

(defn -main
  ""
  [& args]
  (let [opts (parse-opts args cli-options)]
    (println (json/write-str
              (single-machine-inventory (-> opts :options :user)
                                        (-> opts :options :host)
                                        (-> opts :options :port))))))
