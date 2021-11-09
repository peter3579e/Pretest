# Pretest
Objective
Create a reusable CurrencyListFragment
Also create a DemoActivity to showcase the CurrencyListFragment
Please upload to github with a random project name
Requirement

# Overview
- CurrencyListFragment should receive an array list of CurrencyInfo to create the ui.
- DemoActivity should provide 1 dataset, Currency List A of CurrencyInfo to
CurrencyListFragment and the dataset should be queried from local db
- DemoActivity should provide 2 buttons to do the demo.
- First button to load the data and display
- Second button for sorting currency list
- CurrencyListFragment should provide a hook of item click listener to the parent
- All the IO operations MUST NOT be in UI Thread.
- Please show how to handle multi threading operation, and deal with concurrency
issue when do sorting (fast double click of sorting button)
- Search functionality is not required
- Unit test is welcome
