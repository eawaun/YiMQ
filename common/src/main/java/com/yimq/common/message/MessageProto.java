// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/protobuf/message/Message.proto

package com.yimq.common.message;

public final class MessageProto {
  private MessageProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface MessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.yimq.common.protobuf.message.Message)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 queueId = 1;</code>
     */
    boolean hasQueueId();
    /**
     * <code>optional int32 queueId = 1;</code>
     */
    int getQueueId();

    /**
     * <code>optional int32 delayTime = 2;</code>
     */
    boolean hasDelayTime();
    /**
     * <code>optional int32 delayTime = 2;</code>
     */
    int getDelayTime();

    /**
     * <code>optional string topic = 3;</code>
     */
    boolean hasTopic();
    /**
     * <code>optional string topic = 3;</code>
     */
    java.lang.String getTopic();
    /**
     * <code>optional string topic = 3;</code>
     */
    com.google.protobuf.ByteString
        getTopicBytes();

    /**
     * <code>optional bytes body = 4;</code>
     */
    boolean hasBody();
    /**
     * <code>optional bytes body = 4;</code>
     */
    com.google.protobuf.ByteString getBody();
  }
  /**
   * Protobuf type {@code com.yimq.common.protobuf.message.Message}
   */
  public  static final class Message extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.yimq.common.protobuf.message.Message)
      MessageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Message.newBuilder() to construct.
    private Message(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Message() {
      queueId_ = 0;
      delayTime_ = 0;
      topic_ = "";
      body_ = com.google.protobuf.ByteString.EMPTY;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Message(
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
            case 8: {
              bitField0_ |= 0x00000001;
              queueId_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              delayTime_ = input.readInt32();
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000004;
              topic_ = bs;
              break;
            }
            case 34: {
              bitField0_ |= 0x00000008;
              body_ = input.readBytes();
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
      return com.yimq.common.message.MessageProto.internal_static_com_yimq_common_protobuf_message_Message_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.yimq.common.message.MessageProto.internal_static_com_yimq_common_protobuf_message_Message_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.yimq.common.message.MessageProto.Message.class, com.yimq.common.message.MessageProto.Message.Builder.class);
    }

    private int bitField0_;
    public static final int QUEUEID_FIELD_NUMBER = 1;
    private int queueId_;
    /**
     * <code>optional int32 queueId = 1;</code>
     */
    public boolean hasQueueId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 queueId = 1;</code>
     */
    public int getQueueId() {
      return queueId_;
    }

    public static final int DELAYTIME_FIELD_NUMBER = 2;
    private int delayTime_;
    /**
     * <code>optional int32 delayTime = 2;</code>
     */
    public boolean hasDelayTime() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 delayTime = 2;</code>
     */
    public int getDelayTime() {
      return delayTime_;
    }

    public static final int TOPIC_FIELD_NUMBER = 3;
    private volatile java.lang.Object topic_;
    /**
     * <code>optional string topic = 3;</code>
     */
    public boolean hasTopic() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional string topic = 3;</code>
     */
    public java.lang.String getTopic() {
      java.lang.Object ref = topic_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          topic_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string topic = 3;</code>
     */
    public com.google.protobuf.ByteString
        getTopicBytes() {
      java.lang.Object ref = topic_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        topic_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int BODY_FIELD_NUMBER = 4;
    private com.google.protobuf.ByteString body_;
    /**
     * <code>optional bytes body = 4;</code>
     */
    public boolean hasBody() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional bytes body = 4;</code>
     */
    public com.google.protobuf.ByteString getBody() {
      return body_;
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
        output.writeInt32(1, queueId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, delayTime_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, topic_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeBytes(4, body_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, queueId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, delayTime_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, topic_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(4, body_);
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
      if (!(obj instanceof com.yimq.common.message.MessageProto.Message)) {
        return super.equals(obj);
      }
      com.yimq.common.message.MessageProto.Message other = (com.yimq.common.message.MessageProto.Message) obj;

      boolean result = true;
      result = result && (hasQueueId() == other.hasQueueId());
      if (hasQueueId()) {
        result = result && (getQueueId()
            == other.getQueueId());
      }
      result = result && (hasDelayTime() == other.hasDelayTime());
      if (hasDelayTime()) {
        result = result && (getDelayTime()
            == other.getDelayTime());
      }
      result = result && (hasTopic() == other.hasTopic());
      if (hasTopic()) {
        result = result && getTopic()
            .equals(other.getTopic());
      }
      result = result && (hasBody() == other.hasBody());
      if (hasBody()) {
        result = result && getBody()
            .equals(other.getBody());
      }
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
      if (hasQueueId()) {
        hash = (37 * hash) + QUEUEID_FIELD_NUMBER;
        hash = (53 * hash) + getQueueId();
      }
      if (hasDelayTime()) {
        hash = (37 * hash) + DELAYTIME_FIELD_NUMBER;
        hash = (53 * hash) + getDelayTime();
      }
      if (hasTopic()) {
        hash = (37 * hash) + TOPIC_FIELD_NUMBER;
        hash = (53 * hash) + getTopic().hashCode();
      }
      if (hasBody()) {
        hash = (37 * hash) + BODY_FIELD_NUMBER;
        hash = (53 * hash) + getBody().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.yimq.common.message.MessageProto.Message parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yimq.common.message.MessageProto.Message parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yimq.common.message.MessageProto.Message parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yimq.common.message.MessageProto.Message parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yimq.common.message.MessageProto.Message parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yimq.common.message.MessageProto.Message parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yimq.common.message.MessageProto.Message parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.yimq.common.message.MessageProto.Message parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.yimq.common.message.MessageProto.Message parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.yimq.common.message.MessageProto.Message parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.yimq.common.message.MessageProto.Message parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.yimq.common.message.MessageProto.Message parseFrom(
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
    public static Builder newBuilder(com.yimq.common.message.MessageProto.Message prototype) {
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
     * Protobuf type {@code com.yimq.common.protobuf.message.Message}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.yimq.common.protobuf.message.Message)
        com.yimq.common.message.MessageProto.MessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.yimq.common.message.MessageProto.internal_static_com_yimq_common_protobuf_message_Message_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.yimq.common.message.MessageProto.internal_static_com_yimq_common_protobuf_message_Message_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.yimq.common.message.MessageProto.Message.class, com.yimq.common.message.MessageProto.Message.Builder.class);
      }

      // Construct using com.yimq.common.message.MessageProto.Message.newBuilder()
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
        queueId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        delayTime_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        topic_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        body_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.yimq.common.message.MessageProto.internal_static_com_yimq_common_protobuf_message_Message_descriptor;
      }

      public com.yimq.common.message.MessageProto.Message getDefaultInstanceForType() {
        return com.yimq.common.message.MessageProto.Message.getDefaultInstance();
      }

      public com.yimq.common.message.MessageProto.Message build() {
        com.yimq.common.message.MessageProto.Message result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.yimq.common.message.MessageProto.Message buildPartial() {
        com.yimq.common.message.MessageProto.Message result = new com.yimq.common.message.MessageProto.Message(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.queueId_ = queueId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.delayTime_ = delayTime_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.topic_ = topic_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.body_ = body_;
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
        if (other instanceof com.yimq.common.message.MessageProto.Message) {
          return mergeFrom((com.yimq.common.message.MessageProto.Message)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.yimq.common.message.MessageProto.Message other) {
        if (other == com.yimq.common.message.MessageProto.Message.getDefaultInstance()) return this;
        if (other.hasQueueId()) {
          setQueueId(other.getQueueId());
        }
        if (other.hasDelayTime()) {
          setDelayTime(other.getDelayTime());
        }
        if (other.hasTopic()) {
          bitField0_ |= 0x00000004;
          topic_ = other.topic_;
          onChanged();
        }
        if (other.hasBody()) {
          setBody(other.getBody());
        }
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
        com.yimq.common.message.MessageProto.Message parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.yimq.common.message.MessageProto.Message) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int queueId_ ;
      /**
       * <code>optional int32 queueId = 1;</code>
       */
      public boolean hasQueueId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 queueId = 1;</code>
       */
      public int getQueueId() {
        return queueId_;
      }
      /**
       * <code>optional int32 queueId = 1;</code>
       */
      public Builder setQueueId(int value) {
        bitField0_ |= 0x00000001;
        queueId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 queueId = 1;</code>
       */
      public Builder clearQueueId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        queueId_ = 0;
        onChanged();
        return this;
      }

      private int delayTime_ ;
      /**
       * <code>optional int32 delayTime = 2;</code>
       */
      public boolean hasDelayTime() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 delayTime = 2;</code>
       */
      public int getDelayTime() {
        return delayTime_;
      }
      /**
       * <code>optional int32 delayTime = 2;</code>
       */
      public Builder setDelayTime(int value) {
        bitField0_ |= 0x00000002;
        delayTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 delayTime = 2;</code>
       */
      public Builder clearDelayTime() {
        bitField0_ = (bitField0_ & ~0x00000002);
        delayTime_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object topic_ = "";
      /**
       * <code>optional string topic = 3;</code>
       */
      public boolean hasTopic() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional string topic = 3;</code>
       */
      public java.lang.String getTopic() {
        java.lang.Object ref = topic_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            topic_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string topic = 3;</code>
       */
      public com.google.protobuf.ByteString
          getTopicBytes() {
        java.lang.Object ref = topic_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          topic_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string topic = 3;</code>
       */
      public Builder setTopic(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        topic_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string topic = 3;</code>
       */
      public Builder clearTopic() {
        bitField0_ = (bitField0_ & ~0x00000004);
        topic_ = getDefaultInstance().getTopic();
        onChanged();
        return this;
      }
      /**
       * <code>optional string topic = 3;</code>
       */
      public Builder setTopicBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        topic_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString body_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>optional bytes body = 4;</code>
       */
      public boolean hasBody() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional bytes body = 4;</code>
       */
      public com.google.protobuf.ByteString getBody() {
        return body_;
      }
      /**
       * <code>optional bytes body = 4;</code>
       */
      public Builder setBody(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        body_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bytes body = 4;</code>
       */
      public Builder clearBody() {
        bitField0_ = (bitField0_ & ~0x00000008);
        body_ = getDefaultInstance().getBody();
        onChanged();
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


      // @@protoc_insertion_point(builder_scope:com.yimq.common.protobuf.message.Message)
    }

    // @@protoc_insertion_point(class_scope:com.yimq.common.protobuf.message.Message)
    private static final com.yimq.common.message.MessageProto.Message DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.yimq.common.message.MessageProto.Message();
    }

    public static com.yimq.common.message.MessageProto.Message getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<Message>
        PARSER = new com.google.protobuf.AbstractParser<Message>() {
      public Message parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Message(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Message> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Message> getParserForType() {
      return PARSER;
    }

    public com.yimq.common.message.MessageProto.Message getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_yimq_common_protobuf_message_Message_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_yimq_common_protobuf_message_Message_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\"src/protobuf/message/Message.proto\022 co" +
      "m.yimq.common.protobuf.message\"J\n\007Messag" +
      "e\022\017\n\007queueId\030\001 \001(\005\022\021\n\tdelayTime\030\002 \001(\005\022\r\n" +
      "\005topic\030\003 \001(\t\022\014\n\004body\030\004 \001(\014B\'\n\027com.yimq.c" +
      "ommon.messageB\014MessageProto"
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
    internal_static_com_yimq_common_protobuf_message_Message_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_yimq_common_protobuf_message_Message_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_yimq_common_protobuf_message_Message_descriptor,
        new java.lang.String[] { "QueueId", "DelayTime", "Topic", "Body", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
