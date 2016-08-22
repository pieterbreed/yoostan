(defproject yoostan "0.0.1-SNAPSHOT"
  :description ""
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha10"]
                 [yoostan-lib "0.0.1-SNAPSHOT"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/tools.cli "0.3.5"]
                 [environ "1.0.3"]]
  :main ^:skip-aot yoostan.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
