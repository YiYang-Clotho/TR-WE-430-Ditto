:: arg is the comment need ""
set comment=%1%
set branch=%2%
git add .
git commit -m %comment%
git pull origin %branch%
git push origin %branch%
