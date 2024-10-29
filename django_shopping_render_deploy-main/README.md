# django_shopping
 
PYTHON
    python -m venv venv
    cd backend\venv\Scripts
    activate

    pip install django
    pip install djangorestframework
    pip install pillow
    pip install django-cors-headers
    pip install djangorestframework-simplejwt
    
    django-admin startproject backend

    python manage.py startapp base          create aplication
    python manage.py makemigrations         apply migrations
    python manage.py makemigrations base    force makemigrations in the project
    python manage.py migrate                need to run when maked alterations in database
    python manage.py runserver              run server
    python manage.py createsuperuser        create super user
    python manage.py flush                  resset database
    python manage.py collectstatic          create static files folder


PARA DEPLOY

    cd backend\venv\Scripts
    source activate
    export DATABASE_URL=SUA_DATABASE_URL_CONECTION
    python manage.py createsuperuser
    python manage.py migrate