// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/protobuf/route/BrokerData.proto

package com.yimq.common.protocol.route;

public final class BrokerDataProto {
  private BrokerDataProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface BrokerDataOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.yimq.common.protobuf.route.BrokerData)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional string cluster = 1;</code>
     */
    boolean hasCluster();
    /**
     * <code>optional string cluster = 1;</code>
     */
    java.lang.String getCluster();
    /**
     * <code>optional string cluster = 1;</code>
     */
    com.google.protobuf.ByteString
        getClusterBytes();

    /**
     * <code>optional string brokerName = 2;</code>
     */
    boolean hasBrokerName();
    /**
     * <code>optional string brokerName = 2;</code>
     */
    java.lang.String getBrokerName();
    /**
     * <code>optional string brokerName = 2;</code>
     */
    com.google.protobuf.ByteString
        getBrokerNameBytes();

    /**
     * <code>map&lt;int32, string&gt; brokerAddrs = 3;</code>
     */
    int getBrokerAddrsCount();
    /**
     * <code>map&lt;int32, string&gt; brokerAddrs = 3;</code>
     */
    boolean containsBrokerAddrs(
        int key);
    /**
     * Use {@link #getBrokerAddrsMap()} instead.
     */
    @java.lang.Deprecated
    java.util.Map<java.lang.Integer, java.lang.String>
    getBrokerAddrs();
    /**
     * <code>map&lt;int32, string&gt; brokerAddrs = 3;</code>
     */
    java.util.Map<java.lang.Integer, java.lang.String>
    getBrokerAddrsMap();
    /**
     * <code>map&lt;int32, string&gt; brokerAddrs = 3;</code>
     */

    java.lang.String getBrokerAddrsOrDefault(
        int key,
        java.lang.String defaultValue);
    /**
     * <code>map&lt;int32, string&gt; brokerAddrs = 3;</code>
     */

    java.lang.String getBrokerAddrsOrThrow(
        int key);
  }
  /**
   * Protobuf type {@code com.yimq.common.protobuf.route.BrokerData}
   */
  public  static final class BrokerData extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.yimq.common.protobuf.route.BrokerData)
      BrokerDataOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use BrokerData.newBuilder() to construct.
    private BrokerData(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private BrokerData() {
      cluster_ = "";
      brokerName_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private BrokerData(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              cluster_ = bs;
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              brokerName_ = bs;
              break;
            }
            case 26: {
              if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
                brokerAddrs_ = com.google.protobuf.MapField.newMapField(
                    BrokerAddrsDefaultEntryHolder.defaultEntry);
                mutable_bitField0_ |= 0x00000004;
              }
              com.google.protobuf.MapEntry<java.lang.Integer, java.lang.String>
              brokerAddrs__ = input.readMessage(
                  BrokerAddrsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
              brokerAddrs_.getMutableMap().put(
                  brokerAddrs__.getKey(), brokerAddrs__.getValue());
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.yimq.common.protocol.route.BrokerDataProto.internal_static_com_yimq_common_protobuf_route_BrokerData_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 3:
          return internalGetBrokerAddrs();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.yimq.common.protocol.route.BrokerDataProto.internal_static_com_yimq_common_protobuf_route_BrokerData_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.yimq.common.protocol.route.BrokerDataProto.BrokerData.class, com.yimq.common.protocol.route.BrokerDataProto.BrokerData.Builder.class);
    }

    private int bitField0_;
    public static final int CLUSTER_FIELD_NUMBER = 1;
    private volatile java.lang.Object cluster_;
    /**
     * <code>optional string cluster = 1;</code>
     */
    public boolean hasCluster() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional string cluster = 1;</code>
     */
    public java.lang.String getCluster() {
      java.lang.Object ref = cluster_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          cluster_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string cluster = 1;</code>
     */
    public com.google.protobuf.ByteString
        getClusterBytes() {
      java.lang.Object ref = cluster_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        cluster_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int BROKERNAME_FIELD_NUMBER = 2;
    private volatile java.lang.Object brokerName_;
    /**
     * <code>optional string brokerName = 2;</code>
     */
    public boolean hasBrokerName() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional string brokerName = 2;</code>
     */
    public java.lang.String getBrokerName() {
      java.lang.Object ref = brokerName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          brokerName_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string brokerName = 2;</code>
     */
    public com.google.protobuf.ByteString
        getBrokerNameBytes() {
      java.lang.Object ref = brokerName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        brokerName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int BROKERADDRS_FIELD_NUMBER = 3;
    private static final class BrokerAddrsDefaultEntryHolder {
      static final com.google.protobuf.MapEntry<
          java.lang.Integer, java.lang.String> defaultEntry =
              com.google.protobuf.MapEntry
              .<java.lang.Integer, java.lang.String>newDefaultInstance(
                  com.yimq.common.protocol.route.BrokerDataProto.internal_static_com_yimq_common_protobuf_route_BrokerData_BrokerAddrsEntry_descriptor, 
                  com.google.protobuf.WireFormat.FieldType.INT32,
                  0,
                  com.google.protobuf.WireFormat.FieldType.STRING,
                  "");
    }
    private com.google.protobuf.MapField<
        java.lang.Integer, java.lang.String> brokerAddrs_;
    private com.google.protobuf.MapField<java.lang.Integer, java.lang.String>
    internalGetBrokerAddrs() {
      if (brokerAddrs_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            BrokerAddrsDefaultEntryHolder.defaultEntry);
      }
      return brokerAddrs_;
    }

    public int getBrokerAddrsCount() {
      return internalGetBrokerAddrs().getMap().size();
    }
    /**
     * <code>map&lt;int32, string&gt; brokerAddrs = 3;</code>
     */

    public boolean containsBrokerAddrs(
        int key) {
      
      return internalGetBrokerAddrs().getMap().containsKey(key);
    }
    /**
     * Use {@link #getBrokerAddrsMap()} instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.Integer, java.lang.String> getBrokerAddrs() {
      return getBrokerAddrsMap();
    }
    /**
     * <code>map&lt;int32, string&gt; brokerAddrs = 3;</code>
     */

    public java.util.Map<java.lang.Integer, java.lang.String> getBrokerAddrsMap() {
      return internalGetBrokerAddrs().getMap();
    }
    /**
     * <code>map&lt;int32, string&gt; brokerAddrs = 3;</code>
     */

    public java.lang.String getBrokerAddrsOrDefault(
        int key,
        java.lang.String defaultValue) {
      
      java.util.Map<java.lang.Integer, java.lang.String> map =
          internalGetBrokerAddrs().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;int32, string&gt; brokerAddrs = 3;</code>
     */

    public java.lang.String getBrokerAddrsOrThrow(
        int key) {
      
      java.util.Map<java.lang.Integer, java.lang.String> map =
          internalGetBrokerAddrs().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, cluster_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, brokerName_);
      }
      com.google.protobuf.GeneratedMessageV3
        .serializeIntegerMapTo(
          output,
          internalGetBrokerAddrs(),
          BrokerAddrsDefaultEntryHolder.defaultEntry,
          3);
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, cluster_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, brokerName_);
      }
      for (java.util.Map.Entry<java.lang.Integer, java.lang.String> entry
           : internalGetBrokerAddrs().getMap().entrySet()) {
        com.google.protobuf.MapEntry<java.lang.Integer, java.lang.String>
        brokerAddrs__ = BrokerAddrsDefaultEntryHolder.defaultEntry.newBuilderForType()
            .setKey(entry.getKey())
            .setValue(entry.getValue())
            .build();
        size += com.google.protobuf.CodedOutputStream
            .computeMessageSize(3, brokerAddrs__);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.yimq.common.protocol.route.BrokerDataProto.BrokerData)) {
        return super.equals(obj);
      }
      com.yimq.common.protocol.route.BrokerDataProto.BrokerData other = (com.yimq.common.protocol.route.BrokerDataProto.BrokerData) obj;

      boolean result = true;
      result = result && (hasCluster() == other.hasCluster());
      if (hasCluster()) {
        result = result && getCluster()
            .equals(other.getCluster());
      }
      result = result && (hasBrokerName() == other.hasBrokerName());
      if (hasBrokerName()) {
        result = result && getBrokerName()
            .equals(other.getBrokerName());
      }
      result = result && internalGetBrokerAddrs().equals(
          other.internalGetBrokerAddrs());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasCluster()) {
        hash = (37 * hash) + CLUSTER_FIELD_NUMBER;
        hash = (53 * hash) + getCluster().hashCode();
      }
      if (hasBrokerName()) {
        hash = (37 * hash) + BROKERNAME_FIELD_NUMBER;
        hash = (53 * hash) + getBrokerName().hashCode();
      }
      if (!internalGetBrokerAddrs().getMap().isEmpty()) {
        hash = (37 * hash) + BROKERADDRS_FIELD_NUMBER;
        hash = (53 * hash) + internalGetBrokerAddrs().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.yimq.common.protocol.route.BrokerDataProto.BrokerData parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yimq.common.protocol.route.BrokerDataProto.BrokerData parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yimq.common.protocol.route.BrokerDataProto.BrokerData parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yimq.common.protocol.route.BrokerDataProto.BrokerData parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yimq.common.protocol.route.BrokerDataProto.BrokerData parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yimq.common.protocol.route.BrokerDataProto.BrokerData parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yimq.common.protocol.route.BrokerDataProto.BrokerData parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.yimq.common.protocol.route.BrokerDataProto.BrokerData parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.yimq.common.protocol.route.BrokerDataProto.BrokerData parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.yimq.common.protocol.route.BrokerDataProto.BrokerData parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.yimq.common.protocol.route.BrokerDataProto.BrokerData parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.yimq.common.protocol.route.BrokerDataProto.BrokerData parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.yimq.common.protocol.route.BrokerDataProto.BrokerData prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.yimq.common.protobuf.route.BrokerData}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.yimq.common.protobuf.route.BrokerData)
        com.yimq.common.protocol.route.BrokerDataProto.BrokerDataOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.yimq.common.protocol.route.BrokerDataProto.internal_static_com_yimq_common_protobuf_route_BrokerData_descriptor;
      }

      @SuppressWarnings({"rawtypes"})
      protected com.google.protobuf.MapField internalGetMapField(
          int number) {
        switch (number) {
          case 3:
            return internalGetBrokerAddrs();
          default:
            throw new RuntimeException(
                "Invalid map field number: " + number);
        }
      }
      @SuppressWarnings({"rawtypes"})
      protected com.google.protobuf.MapField internalGetMutableMapField(
          int number) {
        switch (number) {
          case 3:
            return internalGetMutableBrokerAddrs();
          default:
            throw new RuntimeException(
                "Invalid map field number: " + number);
        }
      }
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.yimq.common.protocol.route.BrokerDataProto.internal_static_com_yimq_common_protobuf_route_BrokerData_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.yimq.common.protocol.route.BrokerDataProto.BrokerData.class, com.yimq.common.protocol.route.BrokerDataProto.BrokerData.Builder.class);
      }

      // Construct using com.yimq.common.protocol.route.BrokerDataProto.BrokerData.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        cluster_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        brokerName_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        internalGetMutableBrokerAddrs().clear();
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.yimq.common.protocol.route.BrokerDataProto.internal_static_com_yimq_common_protobuf_route_BrokerData_descriptor;
      }

      public com.yimq.common.protocol.route.BrokerDataProto.BrokerData getDefaultInstanceForType() {
        return com.yimq.common.protocol.route.BrokerDataProto.BrokerData.getDefaultInstance();
      }

      public com.yimq.common.protocol.route.BrokerDataProto.BrokerData build() {
        com.yimq.common.protocol.route.BrokerDataProto.BrokerData result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.yimq.common.protocol.route.BrokerDataProto.BrokerData buildPartial() {
        com.yimq.common.protocol.route.BrokerDataProto.BrokerData result = new com.yimq.common.protocol.route.BrokerDataProto.BrokerData(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.cluster_ = cluster_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.brokerName_ = brokerName_;
        result.brokerAddrs_ = internalGetBrokerAddrs();
        result.brokerAddrs_.makeImmutable();
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.yimq.common.protocol.route.BrokerDataProto.BrokerData) {
          return mergeFrom((com.yimq.common.protocol.route.BrokerDataProto.BrokerData)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.yimq.common.protocol.route.BrokerDataProto.BrokerData other) {
        if (other == com.yimq.common.protocol.route.BrokerDataProto.BrokerData.getDefaultInstance()) return this;
        if (other.hasCluster()) {
          bitField0_ |= 0x00000001;
          cluster_ = other.cluster_;
          onChanged();
        }
        if (other.hasBrokerName()) {
          bitField0_ |= 0x00000002;
          brokerName_ = other.brokerName_;
          onChanged();
        }
        internalGetMutableBrokerAddrs().mergeFrom(
            other.internalGetBrokerAddrs());
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.yimq.common.protocol.route.BrokerDataProto.BrokerData parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.yimq.common.protocol.route.BrokerDataProto.BrokerData) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object cluster_ = "";
      /**
       * <code>optional string cluster = 1;</code>
       */
      public boolean hasCluster() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional string cluster = 1;</code>
       */
      public java.lang.String getCluster() {
        java.lang.Object ref = cluster_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            cluster_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string cluster = 1;</code>
       */
      public com.google.protobuf.ByteString
          getClusterBytes() {
        java.lang.Object ref = cluster_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          cluster_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string cluster = 1;</code>
       */
      public Builder setCluster(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        cluster_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string cluster = 1;</code>
       */
      public Builder clearCluster() {
        bitField0_ = (bitField0_ & ~0x00000001);
        cluster_ = getDefaultInstance().getCluster();
        onChanged();
        return this;
      }
      /**
       * <code>optional string cluster = 1;</code>
       */
      public Builder setClusterBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        cluster_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object brokerName_ = "";
      /**
       * <code>optional string brokerName = 2;</code>
       */
      public boolean hasBrokerName() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional string brokerName = 2;</code>
       */
      public java.lang.String getBrokerName() {
        java.lang.Object ref = brokerName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            brokerName_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string brokerName = 2;</code>
       */
      public com.google.protobuf.ByteString
          getBrokerNameBytes() {
        java.lang.Object ref = brokerName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          brokerName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string brokerName = 2;</code>
       */
      public Builder setBrokerName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        brokerName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string brokerName = 2;</code>
       */
      public Builder clearBrokerName() {
        bitField0_ = (bitField0_ & ~0x00000002);
        brokerName_ = getDefaultInstance().getBrokerName();
        onChanged();
        return this;
      }
      /**
       * <code>optional string brokerName = 2;</code>
       */
      public Builder setBrokerNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        brokerName_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.MapField<
          java.lang.Integer, java.lang.String> brokerAddrs_;
      private com.google.protobuf.MapField<java.lang.Integer, java.lang.String>
      internalGetBrokerAddrs() {
        if (brokerAddrs_ == null) {
          return com.google.protobuf.MapField.emptyMapField(
              BrokerAddrsDefaultEntryHolder.defaultEntry);
        }
        return brokerAddrs_;
      }
      private com.google.protobuf.MapField<java.lang.Integer, java.lang.String>
      internalGetMutableBrokerAddrs() {
        onChanged();;
        if (brokerAddrs_ == null) {
          brokerAddrs_ = com.google.protobuf.MapField.newMapField(
              BrokerAddrsDefaultEntryHolder.defaultEntry);
        }
        if (!brokerAddrs_.isMutable()) {
          brokerAddrs_ = brokerAddrs_.copy();
        }
        return brokerAddrs_;
      }

      public int getBrokerAddrsCount() {
        return internalGetBrokerAddrs().getMap().size();
      }
      /**
       * <code>map&lt;int32, string&gt; brokerAddrs = 3;</code>
       */

      public boolean containsBrokerAddrs(
          int key) {
        
        return internalGetBrokerAddrs().getMap().containsKey(key);
      }
      /**
       * Use {@link #getBrokerAddrsMap()} instead.
       */
      @java.lang.Deprecated
      public java.util.Map<java.lang.Integer, java.lang.String> getBrokerAddrs() {
        return getBrokerAddrsMap();
      }
      /**
       * <code>map&lt;int32, string&gt; brokerAddrs = 3;</code>
       */

      public java.util.Map<java.lang.Integer, java.lang.String> getBrokerAddrsMap() {
        return internalGetBrokerAddrs().getMap();
      }
      /**
       * <code>map&lt;int32, string&gt; brokerAddrs = 3;</code>
       */

      public java.lang.String getBrokerAddrsOrDefault(
          int key,
          java.lang.String defaultValue) {
        
        java.util.Map<java.lang.Integer, java.lang.String> map =
            internalGetBrokerAddrs().getMap();
        return map.containsKey(key) ? map.get(key) : defaultValue;
      }
      /**
       * <code>map&lt;int32, string&gt; brokerAddrs = 3;</code>
       */

      public java.lang.String getBrokerAddrsOrThrow(
          int key) {
        
        java.util.Map<java.lang.Integer, java.lang.String> map =
            internalGetBrokerAddrs().getMap();
        if (!map.containsKey(key)) {
          throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
      }

      public Builder clearBrokerAddrs() {
        internalGetMutableBrokerAddrs().getMutableMap()
            .clear();
        return this;
      }
      /**
       * <code>map&lt;int32, string&gt; brokerAddrs = 3;</code>
       */

      public Builder removeBrokerAddrs(
          int key) {
        
        internalGetMutableBrokerAddrs().getMutableMap()
            .remove(key);
        return this;
      }
      /**
       * Use alternate mutation accessors instead.
       */
      @java.lang.Deprecated
      public java.util.Map<java.lang.Integer, java.lang.String>
      getMutableBrokerAddrs() {
        return internalGetMutableBrokerAddrs().getMutableMap();
      }
      /**
       * <code>map&lt;int32, string&gt; brokerAddrs = 3;</code>
       */
      public Builder putBrokerAddrs(
          int key,
          java.lang.String value) {
        
        if (value == null) { throw new java.lang.NullPointerException(); }
        internalGetMutableBrokerAddrs().getMutableMap()
            .put(key, value);
        return this;
      }
      /**
       * <code>map&lt;int32, string&gt; brokerAddrs = 3;</code>
       */

      public Builder putAllBrokerAddrs(
          java.util.Map<java.lang.Integer, java.lang.String> values) {
        internalGetMutableBrokerAddrs().getMutableMap()
            .putAll(values);
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.yimq.common.protobuf.route.BrokerData)
    }

    // @@protoc_insertion_point(class_scope:com.yimq.common.protobuf.route.BrokerData)
    private static final com.yimq.common.protocol.route.BrokerDataProto.BrokerData DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.yimq.common.protocol.route.BrokerDataProto.BrokerData();
    }

    public static com.yimq.common.protocol.route.BrokerDataProto.BrokerData getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<BrokerData>
        PARSER = new com.google.protobuf.AbstractParser<BrokerData>() {
      public BrokerData parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new BrokerData(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<BrokerData> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<BrokerData> getParserForType() {
      return PARSER;
    }

    public com.yimq.common.protocol.route.BrokerDataProto.BrokerData getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_yimq_common_protobuf_route_BrokerData_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_yimq_common_protobuf_route_BrokerData_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_yimq_common_protobuf_route_BrokerData_BrokerAddrsEntry_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_yimq_common_protobuf_route_BrokerData_BrokerAddrsEntry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n#src/protobuf/route/BrokerData.proto\022\036c" +
      "om.yimq.common.protobuf.route\"\267\001\n\nBroker" +
      "Data\022\017\n\007cluster\030\001 \001(\t\022\022\n\nbrokerName\030\002 \001(" +
      "\t\022P\n\013brokerAddrs\030\003 \003(\0132;.com.yimq.common" +
      ".protobuf.route.BrokerData.BrokerAddrsEn" +
      "try\0322\n\020BrokerAddrsEntry\022\013\n\003key\030\001 \001(\005\022\r\n\005" +
      "value\030\002 \001(\t:\0028\001B1\n\036com.yimq.common.proto" +
      "col.routeB\017BrokerDataProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_yimq_common_protobuf_route_BrokerData_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_yimq_common_protobuf_route_BrokerData_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_yimq_common_protobuf_route_BrokerData_descriptor,
        new java.lang.String[] { "Cluster", "BrokerName", "BrokerAddrs", });
    internal_static_com_yimq_common_protobuf_route_BrokerData_BrokerAddrsEntry_descriptor =
      internal_static_com_yimq_common_protobuf_route_BrokerData_descriptor.getNestedTypes().get(0);
    internal_static_com_yimq_common_protobuf_route_BrokerData_BrokerAddrsEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_yimq_common_protobuf_route_BrokerData_BrokerAddrsEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
