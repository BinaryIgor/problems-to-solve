#!/usr/bin/env bash
set -euo pipefail
BASE_URL="${BASE_URL:-http://localhost:8080}"

curl -i --get "$BASE_URL/books" \
  --data-urlencode 'tag=classic' \
  --data-urlencode 'tag=programming'

echo