# project-react-python
 
python -m venv venv
cd venv/scripts
activate
django-admin startproject backend
pip install djangorestframework
pip install django-cors-headers
pip install psycopg2

python manage.py startapp core
python manage.py createsuperuser --email admin@example.com --username admin
python manage.py migrate
python manage.py makemigrations
python manage.py makemigrations core

npx create-react-app frontend