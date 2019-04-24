<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".DisplayMessageActivity"
    tools:layout_editor_absoluteY="25dp">

    <TableLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/myTable"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_marginBottom="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:stretchColumns="1"
        android:background="#FFFFFF"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">
        android:background="@drawable/background">

        <TableRow>
            android:background="#92C94A"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_margin="1dp"

            <TextView
                android:id="@+id/textView1"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:padding="3dip"
                android:text="Name of WiFi"/>

            <TextView
                android:id="@+id/textView2"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"
                android:text="MAC Address / Frequency"/>

            <TextView
                android:id="@+id/textView3"
                android:layout_width="50dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"
                android:text="Signal Strength"/>
        </TableRow>

        <TableRow>
            android:background="#FFFFFF"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_margin="1dp"

            <TextView
                android:id="@+id/row1Name"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row1MAC"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row1Strength"
                android:layout_width="50dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>
        </TableRow>

        <TableRow>
            android:background="#FFFFFF"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_margin="1dp"

            <TextView
                android:id="@+id/row2Name"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row2MAC"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row2Strength"
                android:layout_width="50dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>
        </TableRow>

        <TableRow>
            android:background="#FFFFFF"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_margin="1dp"

            <TextView
                android:id="@+id/row3Name"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row3MAC"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row3Strength"
                android:layout_width="50dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>
        </TableRow>

        <TableRow>
            android:background="#FFFFFF"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_margin="1dp"

            <TextView
                android:id="@+id/row4Name"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row4MAC"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row4Strength"
                android:layout_width="50dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>
        </TableRow>

        <TableRow>
            android:background="#FFFFFF"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_margin="1dp"

            <TextView
                android:id="@+id/row5Name"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row5MAC"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row5Strength"
                android:layout_width="50dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>
        </TableRow>

        <TableRow>
            android:background="#FFFFFF"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_margin="1dp"

            <TextView
                android:id="@+id/row6Name"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row6MAC"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row6Strength"
                android:layout_width="50dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>
        </TableRow>

        <TableRow>
            android:background="#FFFFFF"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_margin="1dp"

            <TextView
                android:id="@+id/row7Name"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row7MAC"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row7Strength"
                android:layout_width="50dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>
        </TableRow>
        <TableRow>
            android:background="#FFFFFF"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_margin="1dp"

            <TextView
                android:id="@+id/row8Name"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row8MAC"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row8Strength"
                android:layout_width="50dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>
        </TableRow>

        <TableRow>
            android:background="#FFFFFF"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_margin="1dp"

            <TextView
                android:id="@+id/row9Name"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row9MAC"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row9Strength"
                android:layout_width="50dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>
        </TableRow>

        <TableRow>
            android:background="#FFFFFF"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_margin="1dp"

            <TextView
                android:id="@+id/row10Name"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row10MAC"
                android:layout_width="100dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>

            <TextView
                android:id="@+id/row10Strength"
                android:layout_width="50dp"
                android:layout_height="25dp"
                android:gravity="right"
                android:padding="3dip"/>
        </TableRow>

    </TableLayout>

</android.support.constraint.ConstraintLayout>
