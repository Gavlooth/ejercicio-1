(defproject emtec-reports "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "LATEST"]
                 [com.taoensso/timbre "LATEST"]
                 [thheller/shadow-cljs "LATEST"]
                 [org.clojure/tools.namespace "LATEST"]
                 [aero "1.1.3"]
                 [com.cognitect/transit-cljs "LATEST"]
                 ;; back end
                 [ring/ring-jetty-adapter "LATEST"]
                 [metosin/reitit "LATEST"]
                 [mount "LATEST"]
                 [ring/ring-jetty-adapter "LATEST"]
                 [metosin/jsonista "LATEST"]
                 [org.clojure/core.async "LATEST"]
                 ;; [honeysql "0.9.4"]
                 ;;Frontend
                 [reagent    "LATEST"]
                 [secretary  "LATEST"]
                 [venantius/accountant  "LATEST"]
                 [re-frame   "LATEST"]]
;  :main ^:skip-aot emtec-reports.core

  :plugins [[lein-sass "0.4.0"]]

  :source-paths ["src/cljs" "src/clj" "src/cljc"]
  :sass {:src "resources/sass"
         :output-directory "resources/public/css"}

  :hooks [leiningen.sass]
  :aliases {"js-watch" ["run" "-m" "shadow.cljs.devtools.cli" "watch" "app"]}
  :target-path "target/%s"
  :repl-options {:port 10002}
  :profiles {:uberjar {:aot :all}})
