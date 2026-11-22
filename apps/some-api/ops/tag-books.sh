#!/usr/bin/env bash
set -euo pipefail
BASE_URL="${BASE_URL:-http://localhost:8080}"

curl -i \
  -X POST "$BASE_URL/books-tag" \
  -H 'Content-Type: application/json' \
  -d '{"bookIds":[1,2],"tags":["classic","programming"]}'

echo