## How to push and pull

### Create a git init file to track all progress
```
Run "git init"
```

### [Clone a fresh copy from a remote repository]
```
git clone <git url>
```
e.g. git clone https://github.com/islashu/techtrekguide.git
try using https instead of SSH

(if you are using jetbrains ide and the gui for git is not showing at the bottom right hand corner, alt+ 0 -> create new git repo)
or run git init and restart the IDE

### Getting the latest information from a remote repository
```
git pull <git url> <branch>
```

### [Pushing to a remote]

### If you want to add a read file as instructions you can use 
```
git add README.md
```

### To add all files before committing
```
git add .
```

### Create a local branch
```
git branch <name>
```

### Checkout to that branch
```
git checkout <branch_name>
```

### To commit all files

You have to commit your files before you can push them to a remote repository. 
```
git commit -m "initial commit"
```

### Create a new local branch from which you can commit your message to
Switch to and create the branch, -M is to rename branch and if there is a duplicate, remove it and make this the only branch with that name
```
git branch -M master
```

### Set the origin location of where you want to push to
e.g. git remote add guide https://github.com/islashu/techtrekguide.git
```
git remote add name <remote repository URL>
git remote add origin git@github.com:[username]/[reponame].git
```

### Remove a remote repository
```
e.g. git remote remove origin
git remote remove origin
```

### List all remote repositories using git
```
git remote show 
or
git remote -v (detailed version include which url it points to)
```

### Push to a remote repository


1. if you get an a error message like this: git error: failed to push some refs to remote, likely that you did not commit your changes before pushing. 
2. If you have a permission error, 
```
git push -u <remote repo> <name branch>
git push -u origin master
```

### You can check your git details using 
```
git config --list
```

### To exit out of git config
```
Press q
```

### To set your git details using so that you may push to a remote repository
```
git config --global user.name "Your Name"
git config --global user.email "Your email"
```

If after setting your git details and you still cannot push, the repo owner may need to add you as a collaborator to the repo.


### Accidental commits but want to keep the files, maybe due to committing wrong files or wrong message
```
Undo commit, do not revert commit

git reset --soft HEAD~1 (leave files and all the files added as they are as if before commit) or 
git reset HEAD~1 (leave files as they are as if before commit, files previous git add . need to be added again)
```

### If you want reset your branch to a previous version and throw away all changes like to start a new (as if you didn't even code out anything since you started the branch)
```
git reset --hard HEAD~1
```

### If you need the commit that you just deleted
```
git reflog
```

### git revert vs git reset
```
Very similar interms of destroying the existing and uncommitted changes but 
git reset change existing commit history, git revert adds on a new commit history
```

For more info: https://stackoverflow.com/questions/927358/how-do-i-undo-the-most-recent-local-commits-in-git

