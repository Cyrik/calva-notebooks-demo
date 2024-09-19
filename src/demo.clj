(ns demo
  (:require [portal.viewer :as v]))

"https://github.com/Cyrik/calva-notebooks-demo"

(comment
  {::ratio 22/7
   ::long 4611681620380904123

   ::exception (try (/ 1 0) (catch Exception e e))
   ::io-exception (try (slurp "/hello") (catch Exception e e))
   ::user-exception (Exception. "hi")
   ::ex-info (ex-info "My message" {:my :data})

   ::bigint 42N}
  {::booleans #{true false}
   ::nil nil
   ::vector [1 2 4]
   ::character \A
   ::char-seq (seq "hi\n")
   "string-key" "string-value"
   ::list (list 1 2 3)
   ::set #{1 2 3}
   ::ns-symbol 'hello/world
   ::keyword :hello-world
   ::ns-keyword ::hello-world
   ::range (range 10)
   ::nested-vector [1 2 3 [4 5 6]]
   ::special-doubles [##NaN ##Inf ##-Inf]
   ::url-string "https://github.com/djblue/portal"}
  (v/diff
   [{::removed "value"
     ::same-key "same-value"
     ::change-type #{1 2}
     ::deep-change {:a 0}
     ::set #{0 1 2}
     ::vector [::a ::removed ::b]
     ::different-value ::old-key}
    {::added "value"
     ::same-key "same-value"
     ::change-type {:a :b :c :d}
     ::deep-change {:a 1}
     ::set #{1 2 3}
     ::vector [::a ::added ::b]
     ::different-value ::new-key}])
  (v/for
   {::json "{\"hello\": 123}"
    ::edn "^{:portal.viewer/default :portal.viewer/tree} {:hello 123}"
    ::csv "a,b,c\n1,2,3\n4,5,6"
    ::jwt "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiSm9lIENvZGVyIn0.5dlp7GmziL2QS06sZgK4mtaqv0_xX4oFUuTDh1zHK4U"}
    {::json ::v/json
     ::edn ::v/edn
     ::csv ::v/csv
     ::markdown ::v/markdown
     ::jwt ::v/jwt})
  (v/vega-lite
   {:$schema "https://vega.github.io/schema/vega-lite/v5.json"
    :description "A simple pie chart with labels."
    :data
    {:values
     [{:category "a", :value 4}
      {:category "b", :value 6}
      {:category "c", :value 10}
      {:category "d", :value 3}
      {:category "e", :value 7}
      {:category "f", :value 8}]}
    :encoding
    {:theta
     {:field "value"
      :type "quantitative"
      :stack true}
     :color
     {:field "category"
      :type "nominal"
      :legend nil}}
    :layer
    [{:mark {:type "arc", :outerRadius 80}}
     {:mark {:type "text", :radius 90}
      :encoding
      {:text
       {:field "category", :type "nominal"}}}]
    :view {:stroke nil}}))
