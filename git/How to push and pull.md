## How to push and pull

### [Clone a fresh copy from a remote repository]
```
git clone
```

### Getting the latest information from a remote repository
```
git pull <remote repo name> <branch>
```



### [Pushing to a remote]

### Create a git init file to track all progress
```
Run "git init"
```
### If you want to add a read file as instructions you can use 
```
git add README.md
```

### To add all files before committing
```
git add .
```

### Create a local branch
```cookie
git branch <name>
```

### To commit all files

You have to commit your files before you can push them to a remote repository. 
```
git commit -m "initial commit"
```

### Create a new local branch from which you can commit your message to
Switch to the branch you want to commit to
```
git branch -M master
```

### Set the origin location of where you want to push to
e.g. git remote add guide https://github.com/islashu/techtrekguide.git
```
git remote add name <remote repository URL>
```

### List all remote repositories using git
```
git remote show 
or
git remote -v (detailed version include which url it points to)
```

### Push to a remote repository
git push -u <remote repo name> <branch>
```
git push -u origin master
```




