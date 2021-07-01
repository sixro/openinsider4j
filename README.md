# openinsider4j
> A library to get openinsider.com content

![build](https://github.com/sixro/openinsider4j/actions/workflows/maven.yml/badge.svg)

## Summary

  * [Introduction](#intro)
  * [Release](#release)


## <a name="intro"></a>Introduction

This is a Java library that allows to read data found on [openinsider.com](http://openinsider.com).  
For example, it can allow you to get all insider trading activities made by CEOs of companies.


## <a name="release"></a>Release

In order to create a SNAPSHOT release use the following command:

```
    mvn deploy -Prelease
```

On Windows Subsystem Linux, add the following configuration to avoid `gpg` errors:

```
# FIX error provided by gpg when trying to sign jars.
# Found this solution here: https://github.com/keybase/keybase-issues/issues/2798
export GPG_TTY=$(tty)
```
