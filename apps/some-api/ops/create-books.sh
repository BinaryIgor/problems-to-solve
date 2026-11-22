#!/usr/bin/env bash
set -euo pipefail
BASE_URL="${BASE_URL:-http://localhost:8080}"

curl --fail-with-body -i \
  -X POST "$BASE_URL/book" \
  -H 'Content-Type: application/json' \
  -d '{"title":"Clean Code","author":"Robert C. Martin"}'

echo
echo

curl --fail-with-body -i \
  -X POST "$BASE_URL/book" \
  -H 'Content-Type: application/json' \
  -d '{"title":"Designing Data-Intensive Applications","author":"Martin Kleppmann"}'

echo
