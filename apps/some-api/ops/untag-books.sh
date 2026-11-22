#!/usr/bin/env bash
set -euo pipefail
BASE_URL="${BASE_URL:-http://localhost:8080}"

curl -i \
  -X DELETE "$BASE_URL/book-tag" \
  -H 'Content-Type: application/json' \
  -d '{"bookIds":[1,2],"tags":["classic"]}'

echo