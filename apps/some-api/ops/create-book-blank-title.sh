#!/usr/bin/env bash
set -euo pipefail
BASE_URL="${BASE_URL:-http://localhost:8080}"

curl -i \
  -X POST "$BASE_URL/book" \
  -H 'Content-Type: application/json' \
  -d '{"title":"   ","author":"Jane Doe"}'

echo